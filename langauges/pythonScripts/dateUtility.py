from datetime import datetime, timedelta

class DateUtility:

    def __init__(self):
        pass

    def get_current_date(self):
        return datetime.now()

    def get_date_after_days(self, days):
        return datetime.now() + timedelta(days=days)

    def get_date_before_days(self, days):
        return datetime.now() - timedelta(days=days)

    def get_date_after_months(self, months):
        return datetime.now() + timedelta(days=months*30)

    def get_date_before_months(self, months):
        return datetime.now() - timedelta(days=months*30)

    def get_date_after_years(self, years):
        return datetime.now() + timedelta(days=years*365)

    def get_date_before_years(self, years):
        return datetime.now() - timedelta(days=years*365)

    def get_date_after_hours(self, hours):
        return datetime.now() + timedelta(hours=hours)

    def get_date_before_hours(self, hours):
        return datetime.now() - timedelta(hours=hours)

    def get_date_after_minutes(self, minutes):
        return datetime.now() + timedelta(minutes=minutes)

    def get_date_before_minutes(self, minutes):
        return datetime.now() - timedelta(minutes=minutes)

    def get_date_after_seconds(self, seconds):
        return datetime.now() + timedelta(seconds=seconds)

    def get_date_before_seconds(self, seconds):
        return datetime.now() - timedelta(seconds=seconds)

    def get_date_after_microseconds(self, microseconds):
        return datetime.now() + timedelta(microseconds=microseconds)

    def get_date_before_microseconds(self, microseconds):
        return datetime.now() - timedelta(microseconds=microseconds)

    def get_date_after_milliseconds(self, milliseconds):
        return datetime.now() + timedelta(milliseconds=milliseconds)

    def get_date_before_milliseconds(self, milliseconds):
        return datetime.now() - timedelta(milliseconds=milliseconds)

    def get_date_after_weeks(self, weeks):
        return datetime.now() + timedelta(weeks=weeks)

    def get_date_before_weeks(self, weeks):
        return datetime.now() - timedelta(weeks=weeks)

    def get_date_after_months(self, months):
        return datetime.now() + timedelta(days=months*30)

    def get_date_before_months(self, months):
        return datetime.now() - timedelta(days=months*30)

    def get_date_after_years(self, years):
        return datetime.now() + timedelta(days=years*365)

    def get_date_before_years(self, years):
        return datetime.now() - timedelta(days=years*365)