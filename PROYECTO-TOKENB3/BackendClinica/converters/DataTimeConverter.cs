using System;
using System.Globalization;
using System.Text.Json;
using System.Text.Json.Serialization;


    public class DataTimeConverter : JsonConverter<DateTime>
    {
        private const String Formato = "dd/MM/yyyy";
    public override DateTime Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
    {
        var value = reader.GetString();
        return DateTime.ParseExact(value!, Formato, CultureInfo.InvariantCulture);
    }
    public override void Write(Utf8JsonWriter writer, DateTime value, JsonSerializerOptions options)
        {
            writer.WriteStringValue(value.ToString(Formato));
        }
}

    

