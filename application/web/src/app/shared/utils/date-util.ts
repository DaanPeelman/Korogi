import * as moment from "moment";
import { InvalidDateFormatException } from "../exceptions/invalid-date-format-exception";

export class DateUtil {
    private static readonly DEFAULT_FORMAT = moment.ISO_8601;

    static isValidDate(dateAsString: string): boolean {
        return moment(dateAsString, DateUtil.DEFAULT_FORMAT).isValid();
    }

    static parse(dateAsString: string): Date {
        if (! dateAsString) {
            return undefined;
        } else if (! DateUtil.isValidDate(dateAsString)) {
            throw new InvalidDateFormatException();
        }

        return moment(dateAsString, DateUtil.DEFAULT_FORMAT).toDate();
    }
}
