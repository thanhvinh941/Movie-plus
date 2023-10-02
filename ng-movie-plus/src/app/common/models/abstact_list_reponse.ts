export class AbstractListResponse<T> {
    data!: T[];
    status!: number;
    errors!: string[];
}