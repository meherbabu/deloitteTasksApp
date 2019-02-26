import React from 'react';
import DisplayTasks from './displayTasks';

class TasksList extends React.Component{

  constructor(props){
    super(props);
    this.state = {userId: this.props.userId,
                  tasks : this.props.tasks,
                  username: this.props.username};
  }

  componentWillReceiveProps(nextProps) {
    this.setState({ tasks: nextProps.tasks });
  }

  render(){
    return (
      <div>
      <div className="ui dividing header"></div>
      <div className="ui dividing header">Tasks for the User {this.props.username} </div>
      <table className="ui celled table">
      <thead>
          <tr>
              <th>Select</th>
              <th>Task Id</th>
              <th>Name</th>
              <th>Description</th>
              <th>Last Updated Date</th>
              <th>Delete?</th>
          </tr>
       </thead>
       <tbody>
        <DisplayTasks username={this.state.username} userId={this.state.userId}
                      tasks={this.state.tasks}/>
       </tbody>
      </table>
      </div>
    )
  }

}
export default TasksList;
