import { useState } from "react";

const AutosuggestTextBox = ({ data, value, setValue, onValueChange, onKeyDown, 
	onSubmitKeyDown, placeholder, returnSuggestion, clearInputOnSubmit }) => {
    
	const [suggestions, setSuggestions] = useState([]);
	const [suggestionIndex, setSuggestionIndex] = useState(null);
	const [suggestionsActive, setSuggestionsActive] = useState(false);
	const [inputValue, setInputValue] = useState(value);

	const handleChange = (e) => {
		const query = e.target.value.toLowerCase();
		setInputValue(query);
		if (query.length > 1) {
			const filterSuggestions = data.filter(
				(suggestion) => suggestion.toLowerCase().indexOf(query) > -1
			);
			setSuggestions(filterSuggestions);
			setSuggestionsActive(true);
		} else {
			setSuggestionsActive(false);
		}

		if (onValueChange) {
			onValueChange(query);
		}
	};

	const handleClick = (e) => {
		setSuggestions([]);
		setSuggestionsActive(false);
		returnSuggestion(e.target.innerText);
	};

	const handleKeyDown = (e) => {
		// UP ARROW
		if (e.keyCode === 38) {
			if (suggestionIndex === 0 || suggestionIndex === null) {
				return;
			}
			setSuggestionIndex(suggestionIndex - 1);
		}
		// DOWN ARROW
		else if (e.keyCode === 40) {
			if (suggestionIndex === suggestions.length) {
				return;
			} else if (suggestionIndex === null) {
				setSuggestionIndex(0);
			}
			setSuggestionIndex(suggestionIndex + 1);
		}
		// ENTER
		else if (e.keyCode === 13) {
			if (suggestionIndex !== null) {
				setValue(suggestions[suggestionIndex]);
				setSuggestionIndex(null);
				setSuggestionsActive(false);
			} else {
				setValue(e.target.value);
			}

			if (clearInputOnSubmit) {
				setInputValue("");
			}
		}
		// PROPS
		else if (onKeyDown) {
			onKeyDown(e);
		} else if (onSubmitKeyDown) {
			if (onSubmitKeyDown(e)) {
				setSuggestionIndex(0);
				setSuggestionsActive(false);
			}
		}
	};

	const Suggestions = () => {
		return (
			<ul className="suggestions">
				{suggestions.map((suggestion, index) => {
					return (
						<li
							className={index === suggestionIndex ? "active" : ""}
							key={index}
							onClick={handleClick}
						>
							{suggestion}
						</li>
					);
				})}
			</ul>
		);
	};

	return (
		<div className="AutosuggestTextBox">
		<input
			type="text"
			value={inputValue}
			onChange={handleChange}
			onKeyDown={handleKeyDown}
			placeholder={placeholder}
		/>
		{suggestionsActive && <Suggestions />}
		</div>
	);
  
};

export default AutosuggestTextBox;