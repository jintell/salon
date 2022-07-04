import React, {useState} from "react";
import {useParams} from "react-router-dom";
import Form from 'react-bootstrap/Form';
import {AppNotificationComponent} from "./AppNotificationComponent";

export const GetBillingDetails = (props) => {
    let params = useParams();
    const [validated, setValidated] = useState(false);

    function submitForm(e) {
        e.preventDefault();

        const form = e.currentTarget;
        let validation = false;

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ firstName: e.target.firstName.value,
                lastName: e.target.lastName.value,
                phoneNumber: e.target.phone.value,
                email: e.target.emailAddress.value,
                salonServiceDetailId: params.serviceId,
                slotId: params.slotId})
        };

        if (form.checkValidity() === false) {
            e.stopPropagation();
            AppNotificationComponent.showError("Missing Form Entry " );
            setValidated(true);
        }else{
            validation = true;
            AppNotificationComponent.showSuccess("");

        }


        if(validation === true) {

            fetch('http://localhost:9022/api/payments/initiate', requestOptions)
                .then(response => {
                    if (response.ok) return response.json();
                    throw response;
                }).then(details => {
                props.onSubmit(details);
            }).catch(error => {
                console.log(error.text)
                console.log(error)
                AppNotificationComponent.showError("Error making Payment: " + error.status);
            }).finally(() => {
                console.log("Finally");
            })
        }



    }


        return (
            <Form onSubmit={submitForm}
                  validated={validated}
                style={{textAlign: "left"}}
                  noValidate >
                <h4 style={{textAlign: "left"}}>Enter Billing Details</h4>
                <div className="mb-3">
                    <label htmlFor="firstName" className="form-label">First Name</label>
                    <input type="text" className="form-control"
                           id="firstName" placeholder="Paul" required />
                </div>
                <div className="mb-3">
                    <label htmlFor="lastName"
                           className="form-label">Last Name</label>
                    <input type="text" className="form-control" id="lastName" placeholder="Xavier" required />
                </div>
                <div className="mb-3">
                    <label htmlFor="emailAddress"
                           className="form-label">Email Address</label>
                    <input type="email" className="form-control" id="emailAddress"
                           placeholder="john.smith@yahoo.com" required />
                </div>
                <div className="mb-3">
                    <label htmlFor="phone"
                           className="form-label">Phone Number</label>
                    <input type="text" className="form-control" id="phone" placeholder="+1202177261" required />
                </div>
                <div className="mb-3">
                    <button type="submit"
                            className="btn btn-primary mb-3">Make Payment</button>
                </div>
            </Form>
        );


}
