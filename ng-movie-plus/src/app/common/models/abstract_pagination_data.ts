export class AbstractPaginationData<T> {
    page!: number;
    pageSize!: number;
    totalPages!: number;
    totalRecords!: number;
    records!: T[]
}
