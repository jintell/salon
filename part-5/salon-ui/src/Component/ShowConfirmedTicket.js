import React, { useState, useEffect } from "react";
import {AppNotificationComponent} from "./AppNotificationComponent";
import QRCode from "qrcode.react";

export const ShowConfirmedTicket = (props) => {
        const [serviceInfo, setServiceInfo] = useState({serviceName: '', slotTime: '', stylist: ''});
        const [salonInfo, setSalonInfo] = useState({name: '', address: '', city: '',
            state: '',  zipcode: '', phone: ''});
        const [barCode, setBarcode] = useState();


    useEffect(() => {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Accepts': 'application/json'}
        };

        let id = props.id;
        let ticketId = 0;
        // Create the ticket here and get the details
        fetch(`http://localhost:9022/api/payments/confirm/${id}`, requestOptions)
            .then(response => {
                if(response.ok) return response.json();
                throw response;
            }).then(details => {
            console.log(details)
            ticketId = details.ticket.id;
            setServiceInfo({serviceName: details.ticket.payment.selectedSalonService.name,
                slotTime: details.ticket.payment.selectedSlot.confirmedAt,
                stylist: details.ticket.payment.selectedSlot.stylistName
            })
            setSalonInfo({name: details.salonDetails.name,
                address: details.salonDetails.address,
                city: details.salonDetails.city,
                state: details.salonDetails.state,
                zipcode: details.salonDetails.zipcode,
                phone: details.salonDetails.phone
            })
            setBarcode(`http://localhost:9022/api/tickets/${ticketId}`);

        }).catch(error => {
            console.log(error)
            AppNotificationComponent.showError("Error Creating Customer Ticket: " + error.status);
        }).finally(() => {
            console.log("Finally");
        })

    }, [props]);

    function toLocalTime(time) {
        return time.toLocaleString('en-US', {hour: 'numeric', minute: 'numeric', hour12: true});
    }

        return (
            <div style={{textAlign: "left"}}>
                <div className="row">
                    <h4 style={{textAlign: "left"}}>Your Ticket Details</h4>
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
                        <strong>Salon Details</strong>
                        <br />{salonInfo.name}
                        <br />{salonInfo.address}
                        <br />{salonInfo.city}
                        <br />{salonInfo.state}
                        <br /><span style={{ fontWeight: "bold", color: "#CECECE"}}>Zip</span> {salonInfo.zipcode}
                        <br /><span style={{ fontWeight: "bold", color: "#CECECE"}}>Phone</span> {salonInfo.phone}
                    </div>
                    <div className="col-4">
                        <span style={{fontWeight: "bold", fontSize: "small", display: "block"}}>
                            Take a Picture of the below code and present it to admin</span>

                        <p>
                            <QRCode
                                value={barCode} style={{ marginRight: 50 }}/>
                        </p>
                    </div>
                </div>

            </div>
        );
}