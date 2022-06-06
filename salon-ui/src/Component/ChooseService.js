import React, {Component} from 'react';
import './ChooseService.css'
import {AppNotificationComponent} from "./AppNotificationComponent";

export class ChooseService extends Component {


    constructor(props) {
        super(props);
        this.state = { data: [] }
    }

    componentDidMount() {
        fetch('http://localhost:9022/api/services/retrieveAvailableSalonServices')
            .then(response => {
                if(response.ok) return response.json();
                throw response;
            }).then(data => {
            this.setState({ data: data });
        }).catch(error => {
            AppNotificationComponent.showError("Error Fetching Data: " + error.status);
        }).finally(() => {
            console.log("Finally");
        })

    }



    render() {
        return (
             this.state.data.map( (item, idx) =>
                    <div className="card card-spacing" key={item.id}>
                        <h5 className="card-header">{item.name}</h5>
                        <div className="card-body">
                            <h5 className="card-title">${item.price}</h5>
                            <p className="card-text">
                                {item.description} <br />
                                {item.timeInMinutes} Minutes
                            </p>
                            <button className="btn btn-primary">Book Now</button>
                        </div>
                    </div>
             ) // End of map

        ) // End of JSX
    }
}

