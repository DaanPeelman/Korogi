import { PageMetaData } from "../page-meta-data";

export class PagedResources<T> {
  constructor(
    public resources: T[],
    public metaData: PageMetaData
  ) {
  }
}
