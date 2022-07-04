import TopBarProgress from "react-topbar-progress-indicator";
import TopBar from "react-topbar-progress-indicator";
import {Component} from "react";

TopBar.config({
    barColors: {
        "0": "#008cfd",
        "0.5": "#008cfd",
        "1.0": "#008cfd"
    },
    shadowBlur: 10
});

export class LoadingIndicator extends Component {

    render() {
        return (
            <div style={{ backgroundColor: "#222222" }}>
                {  <TopBarProgress  /> }
            </div>
        );
    }
}
