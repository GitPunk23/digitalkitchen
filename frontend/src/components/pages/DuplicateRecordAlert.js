import React from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

class DuplicateRecordAlert extends React.Component {
	render() {

		const { renderRecordResponse } = this.props;
		const { record } = this.props;

		const onClose = () => { window.location.reload(); }
		const handleClick = () => { renderRecordResponse(record); }

		return (
			<>
				<Modal 
					show={true} 
					onHide={onClose}
					backdrop="static"
					style={{ zIndex:10000}}
				>
					<div style={{
						position: 'fixed',
						top: '50%',
						left: '50%',
						transform: 'translate(-50%, -50%)',
						backgroundColor: '#fff',
						padding: '20px',
						borderRadius: '5px',
						boxShadow: '0 2px 4px rgba(0, 0, 0, 0.2)',
					}}>
						<h3>Duplicate Record Found</h3>
						<div>
							<Button variant="primary" onClick={handleClick}>
								View Recipe
							</Button>
							<Button variant="secondary" onClick={onClose}>
								Close
							</Button>
						</div>
					</div>
				</Modal>
			</>
		);
	}
}

export default DuplicateRecordAlert;
