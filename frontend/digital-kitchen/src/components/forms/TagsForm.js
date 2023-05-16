import React, { useState } from "react";

function TagsForm({ formData, setFormData, onPrevStep }) {
  const [inputValue, setInputValue] = useState("");

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };

  const handleInputKeyDown = (e) => {
    if (e.key === "Enter" || e.key === ",") {
      e.preventDefault();
      const tag = inputValue.trim();
      if (tag) {
        setFormData((prevFormData) => ({
          ...prevFormData,
          tags: [...prevFormData.tags, tag],
        }));
        setInputValue("");
      }
    }
  };

  const handleRemoveTag = (index) => {
    console.log('prevFormData.tags:', prevFormData.tags);
    setFormData((prevFormData) => ({
      ...prevFormData,
      tags: prevFormData.tags.filter((_, i) => i !== index),
    }));
  };
  
  

  return (
    <div>
      <h2>Add Tags</h2>
      <div>
        <input
          type="text"
          placeholder="Enter tags separated by commas"
          value={inputValue}
          onChange={handleInputChange}
          onKeyDown={handleInputKeyDown}
        />
      </div>
      <div>
        {formData && formData.tags && formData.tags.length > 0 ? (
            formData.tags.map((tag, index) => (
                <span key={index} className="tag">
                    {tag}
                    <button onClick={() => handleRemoveTag(index)}>x</button>
                </span>
    ))
  ) : (
    <p>No tags added yet.</p>
  )}
</div>

    </div>
  );
}

export default TagsForm;
