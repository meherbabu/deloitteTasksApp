import React from 'react';

class Task extends React.Component{

constructor(props){
  super(props);
  this.state = {taskId : this.props.task.taskId, deleteButtonStyle : 'ui button disabled', checked : false};
}

 onDelete = (event) => {
   event.preventDefault();
   this.props.onDelete(this.state.taskId);
 }

 handleCheck = () => {
	 this.setState ( {checked : !this.state.checked} );
	 if (this.state.checked){
		 this.setState ( {deleteButtonStyle : 'ui button disabled'} );
	 }else{
		 this.setState ( {deleteButtonStyle : 'ui button'} );
	 }
 }

  render(){
    return (
      <tr>
        <td data-label="Select"> <input type="checkbox" className="ui checkbox" defaultChecked={this.state.checked} onChange={this.handleCheck}/> </td>
        <td data-label="Task Id"> {this.props.task.taskId} </td>
        <td data-label="Name"> {this.props.task.name} </td>
        <td data-label="Description"> {this.props.task.description} </td>
        <td data-label="Last Updated Date"> {this.props.task.updatedDateStr} </td>
        <td data-label="Delete?">
          <button onClick = {this.onDelete} className={this.state.deleteButtonStyle}> Delete</button>
        </td>
      </tr>
    );
  }
}

export default Task;
