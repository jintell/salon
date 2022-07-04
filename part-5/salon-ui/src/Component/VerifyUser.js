import React, { useState } from "react";
// import { QrReader } from "react-qr-reader";
import {AppNotificationComponent} from "./AppNotificationComponent";
import {QRReader} from "./QRReader";


export const VerifyUser = () => {
    const [show, setShow] = useState("show");
    const [serviceInfo, setServiceInfo] = useState({serviceName: '', slotTime: '', stylist: ''});
    const [userInfo, setUserInfo] = useState({name: '', email: '',  zipcode: '', phone: ''});

    let counter = 0;

    function userVerification(showComponent) {

        if(showComponent === "result"){
            return ticketDetails();
        }else if(showComponent === "show"){
            console.log(showComponent)
            return <QRReader onResult={onUserVerification}/>
        }
    }

    function onUserVerification(data) {
            counter++;
            getTicketInfo(data);
            setShow("result");
    }
    function getTicketInfo(url) {

        if(counter <= 1 ) {
            fetch(url)
                .then(response => {
                    if(response.ok) return response.json();
                    throw response;
                }).then(details => {

                setServiceInfo({serviceName: details.payment.selectedSalonService.name,
                    slotTime: details.payment.selectedSlot.confirmedAt,
                    stylist: details.payment.selectedSlot.stylistName
                })
                setUserInfo({name: details.payment.firstName+" " +details.payment.lastName,
                    email: details.payment.email,
                    zipcode: "23401",
                    phone: details.payment.phoneNumber
                })

            }).catch(error => {
                AppNotificationComponent.showError("Invalid Customer : " + error.status);
            }).finally(() => {
                console.log("Finally");
                return;
            })
        }
    }

    function scanAnother(e){
        e.preventDefault();
        window.location.reload();
    }

    function toLocalTime(time) {
        return time.toLocaleString('en-US', {hour: 'numeric', minute: 'numeric', hour12: true});
    }

    function ticketDetails() {
        return (
            <div style={{textAlign: "left"}}>
                <div className="row">
                    <h4 style={{textAlign: "left"}}>Details</h4>
                </div>
                <div className="row">
                    <div className="col-8">
                        <strong>Service Details</strong>
                        <br/>
                        {serviceInfo.serviceName}<span> </span> @ <span> </span>
                        {toLocalTime(new Date(serviceInfo.slotTime))}<span> </span>
                        by {serviceInfo.stylist}
                        <hr />
                        <p></p>
                        <p></p>
                        <strong>User Information</strong>
                        <br />{userInfo.name}
                        <br />{userInfo.email}
                        <br /><span style={{ fontWeight: "bold", color: "#CECECE"}}>Zip</span> {userInfo.zipcode}
                        <br /><span style={{ fontWeight: "bold", color: "#CECECE"}}>Phone</span> {userInfo.phone}
                    </div>
                    <div className="col-4">
                        <span style={{fontWeight: "bold", fontSize: "small", display: "block"}}>
                            <button className="btn btn-primary" onClick={scanAnother}>Scan Another</button></span>

                    </div>
                </div>

            </div>
        )
    }


    return (
        <div>
            {userVerification(show)}
        </div>
    );
}