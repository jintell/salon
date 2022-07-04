import React, { useState } from 'react';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import {AppNotificationComponent} from "./AppNotificationComponent";


export const ChooseDate = (props) => {

    const [startDate, setStartDate] = useState(new Date());
    const [searchTerm, setSearchTerm] = useState({id: 0, selectedDate: new Date()});


    function onFormSubmit(event) {
        event.preventDefault();
        let year = startDate.getFullYear();
        let month = startDate.getMonth() + 1;
        let dayNum = startDate.getDate();
        if(checkDate(year, month, dayNum)) return;

        let fullDate = year + "-" + monthDateFormat(month) + "-" + monthDateFormat(dayNum);
        setSearchTerm( {id: props.id, selectedDate: fullDate});
        props.onSubmit(searchTerm);
    }


    function checkDate(year, month, dayNum){
        let today = new Date();
        let wrongDate = false
        if(year < today.getFullYear()) wrongDate = true;
        else if(year === today.getFullYear() && month < (today.getMonth() + 1)) wrongDate = true;
        else if(year === today.getFullYear() && month === (today.getMonth() + 1) && dayNum < today.getDate() ) wrongDate = true;

        if(wrongDate)
            AppNotificationComponent.showError("Wrong Date selected. Must be greater than or equal to "+today);
        else
            AppNotificationComponent.showSuccess("");
        return wrongDate;
    }

    function monthDateFormat(value) {
        if(value < 10) return "0"+value;
        return value;
    }

    return (
        <div className="container">
            <form className="row" onSubmit={onFormSubmit}>
                <div className="col-3 form-label">
                    <strong>Choose a date for {props.name}</strong>
                </div>
                <div className="col-6">
                    <DatePicker selected={startDate} onChange={(date) => setStartDate(date)} className="form-control"
                                 />
                </div>
                <div className="col-3">
                    <button className="btn btn-primary form-control"
                    >Show Slots</button>
                </div>

            </form>
        </div>
    )
}



