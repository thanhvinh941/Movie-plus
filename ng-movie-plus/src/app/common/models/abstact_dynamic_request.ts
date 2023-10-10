export class AbstractDynamicRequest {
  limit!: number;
  offset!: number;
  tableName!: string;
  conditionStr!: string | null;
  isForUpdate!: boolean | false;
  listFields!: string[];
  orderBys!: { [key: string]: string };
}
