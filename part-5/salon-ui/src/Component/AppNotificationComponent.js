import { Subject } from 'rxjs';

const subject = new Subject();

export const AppNotificationComponent = {
    showSuccess: message => subject.next({ text: <div className="alert alert-success" role="alert"> {message} </div> }),
    showError: message => subject.next({ text: <div className="alert alert-danger" role="alert"> {message} </div> }),
    getMessage: () => subject.asObservable(),
};