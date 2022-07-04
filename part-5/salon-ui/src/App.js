import './App.css';
import {Component} from 'react'
import {LoadingIndicator} from "./Component/LoadingIndicator";
import {AppNotificationComponent} from "./Component/AppNotificationComponent";
import {ChooseService} from "./Component/ChooseService";
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import {ChooseSlot} from "./Component/ChooseSlot";
import {PaymentContainer} from "./Component/PaymentContainer";
import {VerifyUser} from "./Component/VerifyUser";

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
                <BrowserRouter>
                <LoadingIndicator  />
                <header className="App-header">
                    AR Salon and Day Spa Services
                </header>
                <div>
                   {this.state.messages.text}
                </div>
                <div className="container-fluid">
                        <div className="App-body">
                            <Routes>
                                <Route path="/" exact element={<ChooseService />} />
                                <Route path="/chooseslot/:serviceId/:serviceName" element={<ChooseSlot />} />
                                <Route path="/makepayment/:slotId/:serviceId/:serviceName" element={<PaymentContainer />} />
                                <Route path="/admin/verifyuser" element={<VerifyUser />} />
                            </Routes>
                        </div>


                </div>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;