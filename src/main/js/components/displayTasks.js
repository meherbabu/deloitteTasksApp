import React from 'react';
import axios from 'axios';
import Task from './task';

class DisplayTasks extends React.Component{

  constructor(props){
    super(props);
    this.state = {tasks : this.props.tasks, userId: this.props.userId};
  }

  componentWillReceiveProps(nextProps) {
    this.setState({ tasks: nextProps.tasks });
  }

  getTasks = () => {
    axios.get(`users/${this.state.userId}/tasks`)
	  .then(response => {
      this.setState ( {tasks : response.data} );
	  })
	  .catch(error => {
      console.log(error);
		});
  }

  onDelete = (taskId) => {
    axios.delete(`users/tasks/${taskId}`)
	  .then(response => {
      this.getTasks();
	  })
	  .catch(error => {
			console.log(error);
		});
  }

  render(){
    return (
      this.state.tasks.map ( task => {
            return <Task onDelete={this.onDelete} key={task.taskId} task={task} />
      })
    );
  }

}

export default DisplayTasks;
