import { Button } from "react-bootstrap";

const CreateRecipeForm = ({}) => {


    return (
        <div>
            <RecipeDisplayEditForm />
            <Button>
                Save
            </Button>
            <Button>
                Cancel
            </Button>
        </div>
    );
}