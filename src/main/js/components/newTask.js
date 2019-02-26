import React from 'react';

class NewTask extends React.Component{

  constructor(props){
    super(props);
    this.state = { name : '', desc : '', userId: this.props.userId};
  }

  onSave = (event) =>{
    event.preventDefault();
    this.props.onSave(this.state.name, this.state.desc, this.state.userId);
  }

  onCancel = (event) =>{
    event.preventDefault();
    this.props.onCancel();
  }

  render(){
    return (
      <div>
      <form className="ui form">
      <div className="ui dividing header"/>
      <h4 className="ui dividing header">Create New Task</h4>
        <div className="six wide field required inline">
          <label className="ui label" htmlFor="name"> Task Name: </label>
          <input id="name" className="ui input" type="text" value={this.state.name}
            onChange = { e => this.setState ( {name : e.target.value} )} />
        </div>
        <div className="six wide field required inline">
          <label className="ui label" htmlFor="desc"> Task Description: </label>
          <input id="desc" className="ui input" type="text" value={this.state.desc}
            onChange = { e => this.setState ( {desc : e.target.value} )} />
        </div>

        <button onClick= {this.onSave} className="ui primary button">
          Save
        </button>

        <button onClick={this.onCancel} className="ui button">
          Cancel
        </button>
      </form>

      </div>
    )
  }
}

export default NewTask;
