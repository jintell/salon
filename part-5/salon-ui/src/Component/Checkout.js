import React, {useState} from 'react';
import {CardElement, useElements, useStripe} from "@stripe/react-stripe-js";


export const Checkout = (props) => {
    const stripe = useStripe();
    const elements = useElements();
    const [errorMessage, setErrorMessage] = useState(null);

    const handleSubmit = async (event) => {
        // We don't want to let default form submission happen here,
        // which would refresh the page.
        event.preventDefault();
        const {info} = props;

        if (!stripe || !elements) {
            // Stripe.js has not yet loaded.
            // Make sure to disable form submission until Stripe.js has loaded.
            return;
        }

        const cardElement = elements.getElement(CardElement);

        const {error} = await stripe.confirmCardPayment(info.clientSecret, {
            payment_method: {
                card: cardElement,
                billing_details: {
                    name: info.firstName + ' '+info.lastName,
                },
            },
        });


        if (error) {
            setErrorMessage(error.message);
        } else {
            props.onSubmit(info.id);
        }
    };
    return (
        <form style={{textAlign: "left"}} onSubmit={handleSubmit}>

            <h4 style={{textAlign: "left"}}>Enter Card Details</h4>
            <CardElement />
            <br />
            <button type="submit"
                    className="btn btn-success mb-3">Pay</button>
            {errorMessage && <div>{errorMessage}</div>}
        </form>
    );

}