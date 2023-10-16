import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'toAlphabet' })
export class TransferAlphabet implements PipeTransform {
  transform(value: number | null) {
    return String.fromCharCode(value! - 1 + 'A'.charCodeAt(0));
  }
}
