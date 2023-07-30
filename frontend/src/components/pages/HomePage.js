import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, Row, Col, Button, OverlayTrigger, Tooltip } from 'react-bootstrap';
import '../../styles/HomePage.css';

function HomePage() {
  const [buttonsEnabled, setButtonsEnabled] = useState(false);
  const navigate = useNavigate();

  const handleCreateRecipe = () => {
    navigate('/create');
  };

  const handleBrowseRecipes = () => {
    navigate('/search');
  };

  const handleMealPlanner = () => {};

  const handleGroceryList = () => {};

  const getServerStatus = async () => {
    try {
      const response = await fetch('http://localhost:8080/recipes/status/');
      const status = response.status;
      return status === 202;
    } catch (error) {
      console.error(error);
      return false;
    }
  };

  const enableButtons = () => {
    setButtonsEnabled(true);
  };

  const disableButtons = () => {
    setButtonsEnabled(false);
  };

  // For checking server status
  useEffect(() => {
    const intervalId = setInterval(async () => {
      const isServerOnline = await getServerStatus();
      if (!isServerOnline && buttonsEnabled) {
        disableButtons();
      } else if (isServerOnline && !buttonsEnabled) {
        enableButtons();
      }
    }, 60000);

    // Clear the interval when the component unmounts
    return () => clearInterval(intervalId);
  }, []);

  const renderTooltip = () => (
    <Tooltip id="tooltip">Server is offline</Tooltip>
  );

  const buttonClassName = buttonsEnabled ? 'oval-button' : 'oval-button disabled';

  return (
    <div className="home-page">
      <h1 className="title">Digital Kitchen</h1>
      <Container className="button-container">
        <Row>
          <Col>
              <Button
                variant="primary"
                size="lg"
                className={`${buttonClassName} create-recipe-button`}
                onClick={handleCreateRecipe}
                disabled={buttonsEnabled}
              >
                Create a Recipe
              </Button>
          </Col>
        </Row>
        <Row>
          <Col>
              <Button
                variant="primary"
                size="lg"
                className={`${buttonClassName} browse-recipes-button`}
                onClick={handleBrowseRecipes}
                disabled={buttonsEnabled}
              >
                Browse Recipes
              </Button>
          </Col>
        </Row>
        <Row>
          <Col>
              <Button
                variant="primary"
                size="lg"
                className={`${buttonClassName} meal-planner-button`}
                onClick={handleMealPlanner}
                disabled={true}
              >
                Meal Planner<br></br>(coming soon)
              </Button>
          </Col>
        </Row>
        <Row>
          <Col>
              <Button
                variant="primary"
                size="lg"
                className={`${buttonClassName} grocery-list-button`}
                onClick={handleGroceryList}
                disabled={true}
              >
                Grocery List<br></br>(coming soon)
              </Button>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default HomePage;
