import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const TagsForm = ({ formData, setFormData }) => {
    const [inputValue, setInputValue] = useState('');

    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };

    const handleInputKeyDown = (e) => {
    	if (e.key === 'Enter' || e.key === ',') {
      		e.preventDefault();
      		const tag = inputValue.trim();
      		if (tag) {
        		setFormData((prevFormData) => ({
          		...prevFormData,
          		tags: [...prevFormData.tags, tag],
        		}));
        		setInputValue('');
      		}
   		}
  	};

  	const handleRemoveTag = (index) => {
    	setFormData((prevFormData) => ({
      		...prevFormData,
      		tags: prevFormData.tags.filter((_, i) => i !== index),
    	}));
  	};

	return (
		<div>
			<h2>Add Tags</h2>
			<Form>
				<Form.Group className="mb-3">
					<Form.Control
					type="text"
					placeholder="Enter tags separated by commas"
					value={inputValue}
					onChange={handleInputChange}
					onKeyDown={handleInputKeyDown}
					/>
				</Form.Group>
			</Form>
			<div>
				{formData && formData.tags && formData.tags.length > 0 ? (
				formData.tags.map((tag, index) => (
					<span key={index} className="tag">
						{<Button href="#" className='tags'/>}{tag}
						<Button variant="danger" size="sm" onClick={() => handleRemoveTag(index)}>
							x
						</Button>
					</span>
				))) : (
					<p>No tags added yet.</p>
				)}
			</div>
		</div>
	);
};

export default TagsForm;
