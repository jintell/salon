import {Link, useParams} from 'react-router-dom';
import {ChooseDate} from "./ChooseDate";
import React, {useState} from "react";
import {AppNotificationComponent} from "./AppNotificationComponent";



export function ChooseSlot() {

    const [data, setData] = useState([]);
    const [info, setInfo] = useState("");
    let params = useParams();

    function evaluate(e) {
        if(e instanceof Date) {
            let month = e.getMonth() + 1;
            return e.getFullYear()+"-"+(month < 10? "0"+month: month)+"-"+e.getDate();
        }
        return e;
    }

    function onDateSelected(evt) {


        let url = "http://localhost:9022/api/services/retrieveAvailableSlots/" + evt.id + "/" + evaluate(evt.selectedDate)
        setInfo("Available Slots on "+ evaluate(evt.selectedDate))
        fetch(url)
            .then(response => {
                if(response.ok) return response.json();
                throw response;
            }).then(details => {
            setData( details );
            
        }).catch(error => {
            AppNotificationComponent.showError("Error Fetching Data: Connectivity Problem" );
        }).finally(() => {
            console.log(".");
        })
    }

    function checkAvailability(time) {
            return time.toLocaleString('en-US', {hour: 'numeric', minute: 'numeric', hour12: true});
    }

    function display(time) {
        let today = new Date();
        if(today.getDate() === time.getDate() ) {
            if( time.getHours() <= today.getHours()) {
                return "none";
            }
        }
        return "display";
    }

    function result() {
        return (
            data.map((item) => (
                <div className="card slot-card-spacing" key={item.id} style={{ display: display(new Date(item.slotFor))}}>
                    <h5 className="card-header">{item.selectedService.name} </h5>
                    <div className="card-body">
                        <h5 className="card-title">{item.stylistName}</h5>
                        <p className="card-text">
                            Slots Time {checkAvailability(new Date(item.slotFor))}
                            <br /><br />
                            <Link className="btn btn-primary"
                                  to={`/makepayment/${item.id}/${item.selectedService.id}/${item.selectedService.name}`}>
                                Book this Slot</Link>
                        </p>
                    </div>
                </div>
            ))
        );
    }



       return (
           <div>
                 <ChooseDate name={params.serviceName} id={params.serviceId} onSubmit={onDateSelected} />
               <div>
                   <h5 style={{textAlign: "left" }}> <strong>{info}</strong>  </h5>
                   {result()}
               </div>
           </div>
       );

}