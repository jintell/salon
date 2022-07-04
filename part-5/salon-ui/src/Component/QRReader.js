import React, { useState } from "react";
import { QrReader } from "react-qr-reader";

export const QRReader = (props) => {
    const [data, setData] = useState("No result");

    function handleResult(result, error){
            if (!!result) {
                setData(result?.text);
                props.onResult(result?.text);
            }

            if (!!error) {
                // console.info(error);
            }
    }

    return (
        <div>
        <QrReader
            onResult={handleResult}
            style={{ width: "100%" }}
        />
        <p>{data}</p>
        </div>
    );

}

