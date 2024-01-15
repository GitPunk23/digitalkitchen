import React, { useState } from 'react';

const MealList = ({ meals }) => {
    if (!meals) {
        return <div>Loading...</div>;
      }
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 25;

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = meals.slice(indexOfFirstItem, indexOfLastItem);

  const renderList = () => {
    const groupedAndSortedData = currentItems.reduce((acc, item) => {
      const dateKey = item.date;
      if (!acc[dateKey]) {
        acc[dateKey] = [];
      }
      acc[dateKey].push(item);
      return acc;
    }, {});

    return Object.entries(groupedAndSortedData).map(([date, items]) => (
      <div key={date}>
        <h3>{date}</h3>
        <ul>
          {items.sort((a, b) => a.dataType.localeCompare(b.dataType)).map((meal) => (
            <li key={meal.mealName} onClick={() => handleItemClick(meal)}>
              {meal.mealName} - {meal.mealType} - {meal.mealTags.join(', ')}
            </li>
          ))}
        </ul>
      </div>
    ));
  };

  const handleItemClick = (meal) => {
    console.log(`Clicked on ${meal.mealName}`);
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const renderPagination = () => {
    const pageNumbers = Math.ceil(meals.length / itemsPerPage);
    return (
      <div>
        {Array.from({ length: pageNumbers }).map((_, index) => (
          <button key={index + 1} onClick={() => handlePageChange(index + 1)}>
            {index + 1}
          </button>
        ))}
      </div>
    );
  };

  return (
    <div>
      {renderList()}
      {renderPagination()}
    </div>
  );
};

export default MealList;
