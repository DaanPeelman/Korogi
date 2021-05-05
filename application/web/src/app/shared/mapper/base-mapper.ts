import { DateUtil } from "../utils/date-util";
import { IMapper } from "./mapper";

export abstract class BaseMapper<T> implements IMapper<T> {
    private static deleteTypeAttribute(resource: any): void {
        delete resource["type"];
    }

    private static convertDateStringsToDates(resource: any): void {
        for (const key of Object.keys(resource)) {
            const value = resource[key];

            if (typeof value === "string" && DateUtil.isValidDate(value)) {
                resource[key] = DateUtil.parse(value);
            }
        }
    }

    map(resource: any): T {
        BaseMapper.deleteTypeAttribute(resource);
        BaseMapper.convertDateStringsToDates(resource);

        return Object.assign(
            this.getObjectInstance(),
            resource
        );
    }

    abstract forType(): string;

    protected abstract getObjectInstance(): T;
}
