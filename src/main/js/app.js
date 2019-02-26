
import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';

import LoginPage from './components/LoginPage';
import TasksPage from './components/tasksPage';

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = { errorMsg : '' , isLoggedIn : false, tasks : [], username: '', userId : ''}
	}

	isEmptyFields = (name, password) => {
		if (name === '' || password === ''){
			  return true;
		}else{
			  return false;
		}
	}

	onSignup = (name, password) => {
		if (this.isEmptyFields(name, password)){
			this.setState ( {errorMsg : 'UserName and Password Fields cannot be Empty.'});
		}
		else
		{
		   axios.post('users/signup', {
			    name: name,
			    password: password
		    })
			  .then(response => {
					this.setState({isLoggedIn: true, username: name, userId: response.data});
			  })
			  .catch(error => {
					this.setState ( {errorMsg : `User ${name} already exists. Please login`});
				});
			}
		}

	onLogin = (name, password) => {
		if (this.isEmptyFields(name, password)){
			this.setState ( {errorMsg : 'UserName and Password Fields cannot be Empty.'});
		}
		else
		{
			axios.post('users/login', {
			    name: name,
			    password: password
	       })
		  .then(response => {
				this.setState( {userId : response.data , isLoggedIn: true, username : name} );
		  })
		  .catch(error => {
				this.setState( {isLoggedIn : false,
										errorMsg : `User ${name} does not exist. Please signup`});
			});
		}
	}

	onLogout = () =>{
		this.setState( {isLoggedIn : false, errorMsg : ''} );
	}


	render() {
			if (!this.state.isLoggedIn && !this.state.errorMsg) {
				return (
						<LoginPage onSignup={this.onSignup} onLogin={this.onLogin}/>
				);
			}if (!this.state.isLoggedIn && this.state.errorMsg) {
				return (
					  <div>
							<LoginPage onSignup={this.onSignup} onLogin={this.onLogin}/>
							<div className="ui error message six wide"> {this.state.errorMsg} </div>
						</div>
				);
			}
			else{
				return (
						<TasksPage onLogout={this.onLogout}
											 userId={this.state.userId}
											 username={this.state.username}
						/>
				);
			}
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
