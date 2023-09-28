export class AbstractPaginationRequest {
    page!: number | 0;
    pageSize! : number | 20;
    ignoreDelFlg! : boolean | false;
    orderBys! : {[key: string] : string };
}
