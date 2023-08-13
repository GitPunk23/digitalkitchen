import SearchBar from '../SearchBar';
import SearchResults from '../SearchResults';
import React, { useState, useEffect } from 'react';

const SearchPage = () => {
    const [results, setResults] = useState([]);

    return (
      	<div>
        	<div>
          		<SearchBar setResults={setResults} />
        	</div>
			{results.length > 0 && (
				<div>
					<SearchResults results={results} />
				</div>
			)}
      	</div>
    );
};

export default SearchPage;
