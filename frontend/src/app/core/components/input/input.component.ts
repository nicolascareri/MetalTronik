import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, AbstractControl } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss']
})
export class InputComponent implements OnInit {


  @Input() label = '';
  @Input() placeholder = '';
  @Input() name = '';
  @Input() options = [];
  @Input() maxlength: number;
  @Input() type = 'text';
  @Input() errorMessage = '';
  @Input() parentForm: FormGroup;
  @Input() control: FormControl;
  @Input() disable = false;
  @Input() value = '';
  @Input() rows = 5;
  @Input() required = false;
  @Input() comments: any;
  @Input() commentMode = 1;
  @Input() typeInputFilter = false;
  @Input() disableControl = '';

  @Output() select = new EventEmitter();
  @Output() valueChange = new EventEmitter();
  @Output() commentsChange = new EventEmitter();

  public id: any;
  public showLabel = false;
  public showModalComments = false;
  public isFullscreen = false;
  public previusRows: number;
  public comment: string;
  public update$: Subject<boolean> = new Subject();
  public hasComment = false;


  private content: string;

  constructor() { }

  ngOnInit() {
    this.showLabel = this.name !== '';
    if (this.control === undefined) {
      console.error('Control no definido ', this.name);
    }
    this.previusRows = this.rows;
    this.update$.subscribe(content => {
    });
  }

  get myComments() {

    const comment = this.comments && this.comments[this.id] ?
      this.comments[this.id].comment : null;

    return comment ? comment : '';
  }

  get isDisable() {
    return this.disable ? this.disable : this.control.disabled;
  }

  isInvalid() {
    return this.control.invalid && this.control.dirty;
  }


  get errorType() {
    return this.control.errors;
  }

  deleteComment() {
    delete this.comments[this.id];
    this.showModalComments = false;
  }

  onChange(value) {
    this.select.emit(value.options[value.selectedIndex].text);
  }

  selected(value) {
    this.select.emit(value);
  }

  onValueChange(value) {
    this.valueChange.emit(value);
  }

  isRequired() {
    const validator = this.control.validator ? this.control.validator({} as AbstractControl) : null;
    if ((validator && validator.required && this.control.enabled) || this.required === true) {
      return true;
    }
  }

  formatChange(ev) {
    if (!this.control.dirty) {
      this.control.markAsDirty();
    }
    this.control.setValue(ev);
  }

  onChangePair(value) {
    this.select.emit(this.options[value.selectedIndex - 1]);
  }

  fullscreen() {
    this.isFullscreen = true;
    this.rows = 40;
  }

  fullscreenExit() {
    this.isFullscreen = false;

    this.rows = this.previusRows;
  }

  checkWhitespaces() {
    const inputInstance = this.control.value;
    if (inputInstance !== null) {
      const withOutSpaces = inputInstance.trim();
      const transf = withOutSpaces.split(/[ ]+/);
      const finalInput = transf.join(' ');
      this.control.setValue(finalInput);
    }
  }

}
