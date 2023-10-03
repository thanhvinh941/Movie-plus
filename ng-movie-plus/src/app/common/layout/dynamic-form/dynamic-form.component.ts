import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.css']
})
export class DynamicFormComponent {
  dynamicFormGroup!: FormGroup;
  @Input() model!: {};

  fields = [];

  buildForm() {
    // const formGroupFields = this.getFormControlsFields();
    // this.dynamicFormGroup = new FormGroup(formGroupFields);
  }

  // getFormControlsFields() {
  //   const formGroupFields = {};
  //   for (const field of Object.keys(this.model)) {
  //     formGroupFields[field] = new FormControl("");
  //     this.fields.push(field);
  //   }
  //   return formGroupFields;
  // }
}
