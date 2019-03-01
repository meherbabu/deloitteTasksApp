import React from 'react';

class LoginPage extends React.Component {

	constructor (props){
		super (props);
		this.state = { name: '', password : ''};
	}

	componentDidMount(){
		console.log(this.state.name);
	}

	onSignup = (event) => {
		event.preventDefault();
		this.props.onSignup(this.state.name, this.state.password);
	}

	onLogin = (event) => {
		event.preventDefault();
		this.props.onLogin(this.state.name, this.state.password);
	}

	render() {
		return (

			<div>
			  <p> </p>
				<form className="ui form">
				  <h4 className="ui dividing header">User Tasks Application</h4>
				  <div className="six wide field required inline">
					  <p> </p>
						<label className="ui label" htmlFor="name"> Enter Name: </label>
						<input id="name" className="ui input" type="text" value={this.state.name}
							onChange = { e => this.setState ( {name : e.target.value} )} />
					</div>
					<div className="six wide field required inline">
					  <p> </p>
						<label className="ui label" htmlFor="password"> Enter Password: </label>
						<input id="password" className="ui input" type="password" value={this.state.password}
							onChange = { e => this.setState ( {password : e.target.value} )} />
 					</div>

					<button onClick= {this.onSignup} className="ui button">
						Signup
					</button>

					<button onClick={this.onLogin} className="ui button">
						Login
					</button>
				</form>
			</div>

		);
	}

}

export default LoginPage;
