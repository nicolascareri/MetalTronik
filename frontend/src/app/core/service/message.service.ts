import { Injectable } from '@angular/core';
import { ToastrService } from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private time = 5000;
  private extendedTime = 2500;
  private positionClass =  'toast-bottom-full-width';

  constructor(private toastr: ToastrService) { }

  showError(message){
    this.toastr.error(
      message.body , message.title , {
        timeOut: this.time,
        extendedTimeOut: this.extendedTime,
        progressBar: true,
        positionClass: this.positionClass
      }
    )
  }

  showSuccess(message){
    this.toastr.success(
      message.body, message.title , {
        timeOut: this.time,
        extendedTimeOut: this.extendedTime,
        progressBar: true,
        positionClass: this.positionClass
      }
    )
  }

  showWarning(message){
    this.toastr.warning(
      message.body, message.title , {
        positionClass: this.positionClass,
        
        
      }
    )
  }


}
