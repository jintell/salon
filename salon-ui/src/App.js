import './App.css';
import {Component} from 'react'
import {LoadingIndicator} from "./Component/LoadingIndicator";
import {AppNotificationComponent} from "./Component/AppNotificationComponent";
import {ChooseService} from "./Component/ChooseService";

const testData = [
    { bgColor: "#00695c", completed: 30 },
    { bgColor: "#ef6c00", completed: 53 },
    { bgColor: "#6a1b9a", completed: 60 },
];

class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            messages: "",
            completed: 0
        };
    }




    componentDidMount() {
        // subscribe to home component messages
        this.subscription = AppNotificationComponent.getMessage().subscribe(message => {
            if (message) {
                // add message to local state if not empty
                this.setState({ messages:  message });
            } else {
                // clear messages when empty message received
                this.setState({ messages: "" });
            }
        });


    }



    componentWillUnmount() {
        // unsubscribe to ensure no memory leaks
        this.subscription.unsubscribe();
    }

    render() {
        return (
            <div className="App">
                <LoadingIndicator  />
                <header className="App-header">
                    AR Salon and Day Spa Services
                </header>
                <div>
                   {this.state.messages.text}
                </div>
                <div className="container-fluid">
                    <div className="App-body">
                        <ChooseService />
                    </div>
                </div>
            </div>
        );
    }
}

export default App;