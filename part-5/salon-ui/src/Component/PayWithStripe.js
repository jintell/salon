import React from "react";
import {Elements} from '@stripe/react-stripe-js';
import {loadStripe} from '@stripe/stripe-js';
import {Checkout} from "./Checkout";


const stripePromise = loadStripe(`${process.env.REACT_APP_PUBLIC_KEY}`);

const PayWithStripe = (props) => {


    const options = {
        // passing the client secret obtained in step 2
        clientSecret: props.info.clientSecret,
        // Fully customizable with appearance API.
        appearance: {/*...*/},
    };



    return (
            <Elements stripe={stripePromise} options={options}>
                <Checkout info={props.info} onSubmit={props.onSubmit} />
            </Elements>
    );

}

export default PayWithStripe;