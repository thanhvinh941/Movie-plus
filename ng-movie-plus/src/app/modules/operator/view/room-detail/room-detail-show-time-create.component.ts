import { Component } from '@angular/core';
import { NzModalRef } from 'ng-zorro-antd/modal';

@Component({
  selector: 'room-detail-show-time-component',
  template: `
    <div>
      <p>Modal Content</p>
      <p>Modal Content</p>
      <p>Modal Content</p>
      <p>Modal Content</p>
      <p>Modal Content</p>
    </div>
    <div *nzModalFooter>
      <button nz-button nzType="default" (click)="destroyModal()">
        Custom Callback
      </button>
      <button nz-button nzType="primary" (click)="destroyModal()">
        Custom Submit
      </button>
    </div>
  `,
})
export class RoomDetailShowTimeComponent {
  constructor(private modal: NzModalRef) {}

  destroyModal(): void {
    this.modal.destroy();
  }
}
