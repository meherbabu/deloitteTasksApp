import React from 'react';
import axios from 'axios';
import TasksList from './tasksList';
import NewTask from './newTask';

class TasksPage extends React.Component{

  constructor(props){
    super(props);
    this.state = {
                  userId : this.props.userId,
                  addNewPage : false,
                  tasks : []
                };
    this.getTasks();
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

  addTask = (event) =>{
    event.preventDefault();
    this.setState ({addNewPage : true});
  }

  onSave = (name, desc, userId) => {
    axios.post(`users/${userId}/tasks`, {
	    name: name,
	    description: desc
    })
	  .then(response => {
      this.getTasks();
      this.setState ({addNewPage : false});
	  })
	  .catch(error => {
      console.log(error);
		});
  }

  onCancel = () =>{
    this.setState ({addNewPage : false});
  }

  onLogout = () =>{
    this.props.onLogout();
  }

  render(){
          if (this.state.addNewPage === false){
            return (
              <div>
                <TasksList username={this.props.username} userId={this.state.userId}
                 tasks={this.state.tasks}/>
                <div className="ui diving header"></div>
                  <button onClick={this.addTask} className="ui button">
                    Add New Task
                  </button>
                  <button username={this.props.username} onClick={this.onLogout} className="ui button">
                    Logout
                  </button>
              </div>
            )
          }else{
            return (
                  <NewTask username={this.props.username} userId={this.state.userId}
                   onSave={this.onSave} onCancel={this.onCancel}/>
            );
          }
  }
}



export default TasksPage;
