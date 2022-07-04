import React, {useState} from "react";
import {GetBillingDetails} from "./GetBillingDetails";
import PayWithStripe from "./PayWithStripe";
import {ShowConfirmedTicket} from "./ShowConfirmedTicket";



export const PaymentContainer = () => {
    const [name, setName ] = useState("GetBillDetails");
    const [data, setData ] = useState();
    const [id, setId ] = useState();

    function onMakePayments(paymentInfo) {
        setData(paymentInfo);
        setName("PayWithStripe");
    }

    function onCardPaymentPaid(paymentId) {
        console.log(paymentId);
        setId(paymentId);
        setName("ShowConfirmedTicket");

    }


    function showComponent(compo) {
         if(compo === "PayWithStripe"){
            return <PayWithStripe info={data} onSubmit={onCardPaymentPaid}/>
         }
         else if(compo === "ShowConfirmedTicket") {
             return <ShowConfirmedTicket id={id} />
         }else {
             return <GetBillingDetails onSubmit={onMakePayments} />
         }
    }

        return (
            <div>
                {showComponent(name)}
            </div>
        );
}