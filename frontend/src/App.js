	import React from 'react';
	import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
	import Navbar from './components/navbar';
	import HomePage from './components/pages/HomePage';
	import SearchPage from './components/pages/SearchPage';
	import CreateRecipePage from './components/pages/CreateRecipePage';
	import MealPlannerPage from './components/pages/MealPlannerPage';
	import './styles/App.css';
	import { GroceryListProvider } from './context/GroceryListContext';
	import { MeasurementsProvider } from './context/MeasurementsContext';
	import { CategoriesProvider } from './context/CategoriesContext';
	import { IngredientsProvider } from './context/IngredientsContext';
	import { TagsProvider } from './context/TagsContext';

	function App() {
	return (
		<AppProviders>
		<Router>
			<Navbar />
			<Routes>
			<Route path="/">
				<Route index element={<HomePage />} />
				<Route path="/create" element={<CreateRecipePage />} />
				<Route path="/search" element={<SearchPage />} />
				<Route path="/meals" element={<MealPlannerPage />} />
			</Route>
			</Routes>
		</Router>
		</AppProviders>
	);
	}

	// Custom hook that wraps all your context providers
	function AppProviders({ children }) {
	return (
		<MeasurementsProvider>
		<GroceryListProvider>
		<CategoriesProvider>
		<IngredientsProvider>
		<TagsProvider>
			{children}
		</TagsProvider>
		</IngredientsProvider>
		</CategoriesProvider>
		</GroceryListProvider>
		</MeasurementsProvider>
	);
	}

	export default App;
