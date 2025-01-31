/* eslint-disable react/prop-types */
import { useEffect, useState } from "react";

function GenericConverterForm({ unitConfigs }) {
  const [firstOption, setFirstOption] = useState();

  const [conversionResult, setConversionResult] = useState();

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const formValues = Object.fromEntries(formData.entries());
    formValues["unit-type"] = unitConfigs.name;
    fetch("http://localhost:8080/convert", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formValues),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(`The conversion result is ${data}`);
        const formattedData = parseFloat(data).toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
        const formattedInput = parseFloat(formValues["unit-quantity"]).toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
        setConversionResult({
          from: `${formattedInput} ${unitConfigs.units[formValues["first-unit"]]}`,
          to: `${formattedData} ${unitConfigs.units[formValues["second-unit"]]}`,
        });
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  const handleReset = () => {
    setConversionResult(null);
  };

  useEffect(() => {
    setFirstOption(Object.keys(unitConfigs.units)[0]);
    handleReset();
  }, [unitConfigs]);

  return (
    <div>
      {!conversionResult ? (
        <form
          action=""
          className="flex flex-col space-y-2"
          onSubmit={(e) => handleSubmit(e)}
        >
          <label htmlFor="unit-quantity" className="text-lg font-medium">
            Enter the {unitConfigs.name.toLowerCase()} to convert
          </label>
          <input
            className="border rounded-sm mb-4"
            type="number"
            id="unit-quantity"
            name="unit-quantity"
            required
          />
          <label htmlFor="first-unit" className="text-lg font-medium">
            Unit to convert from
          </label>
          <select
            className="border rounded-sm mb-4"
            name="first-unit"
            id="first-unit"
            value={firstOption}
            onChange={(e) => setFirstOption(e.target.value)}
          >
            {Object.keys(unitConfigs.units).map((unit) => (
              <option value={unit} key={unit}>
                {unit.toUpperCase()}
              </option>
            ))}
          </select>
          <label htmlFor="second-unit" className="text-lg font-medium">
            Unit to convert to
          </label>
          <select
            className="border rounded-sm mb-4"
            name="second-unit"
            id="second-unit"
            defaultValue={Object.keys(unitConfigs.units)[1]}
          >
            {Object.keys(unitConfigs.units).map((unit) =>
              unit.toString() !== firstOption ? (
                <option value={unit} key={unit}>
                  {unit.toUpperCase()}
                </option>
              ) : null
            )}
          </select>
          <button
            type="submit"
            className="border font-medium p-2 rounded-md mt-4 cursor-pointer transition-colors hover:bg-black hover:text-white"
          >
            Convert
          </button>
        </form>
      ) : (
        <div>
          <p className="text-2xl font-medium">Result of your calculation</p>
          <h3 className="text-3xl font-semibold my-2">{ conversionResult.from + ' = ' + conversionResult.to }</h3>
          <button onClick={handleReset} className="border font-medium p-2 rounded-md mt-4 cursor-pointer transition-colors w-full hover:bg-black hover:text-white">Reset</button>
        </div>
      )}
    </div>
  );
}

export default GenericConverterForm;
