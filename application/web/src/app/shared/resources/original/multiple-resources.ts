import { Resource } from "./resource";
import { SingleResource } from "./single-resource";
import { PageMetaData } from "../page-meta-data";

export interface MultipleResources extends Resource {
    content: SingleResource[];
    page: PageMetaData;
}
