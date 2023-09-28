import { AbstractPaginationData } from "./abstract_pagination_data";

export interface AbstractPaginationResponse<T> {
    data: AbstractPaginationData<T>;
    status: number,
    errors: string[]
}
