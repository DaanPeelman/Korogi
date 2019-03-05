import { DateUtil } from "../utils/date-util";
import { IMapper } from "./mapper";

export abstract class BaseMapper<T> implements IMapper<T> {
    map(resource: any): T {
        BaseMapper.deleteTypeAttribute(resource);
        BaseMapper.convertDateStringsToDates(resource);

        return Object.assign(
            this.getObjectInstance(),
            resource
        );
    }

    private static deleteTypeAttribute(resource: any): void {
        delete resource["type"];
    }

    private static convertDateStringsToDates(resource: any): void {
        for (let key of Object.keys(resource)) {
            let value = resource[key];

            if (typeof value === "string" && DateUtil.isValidDate(value)) {
                resource[key] = DateUtil.parse(value);
            }
        }
    }

    protected abstract getObjectInstance(): T;

    abstract forType(): string;
}
