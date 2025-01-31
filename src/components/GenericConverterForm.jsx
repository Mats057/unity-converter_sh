/* eslint-disable react/prop-types */
import { useState } from "react";

function GenericConverterForm({ unitConfigs }) {

  const [firstOption, setFirstOption] = useState();

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const formValues = Object.fromEntries(formData.entries());
    formValues['unit-type'] = unitConfigs.name;
    console.log(formValues);
  }
  return (
    <div>
      <form action="" className="flex flex-col space-y-2" onSubmit={(e) => handleSubmit(e)}>
        <label htmlFor="unit-quantity" className="text-lg font-medium">Enter the {unitConfigs.name.toLowerCase()} to convert</label>
        <input className="border rounded-sm mb-4" type="number" id="unit-quantity" name="unit-quantity" required/>
        <label htmlFor="first-unit" className="text-lg font-medium">Unit to convert from</label>
        <select className="border rounded-sm mb-4" name="first-unit" id="first-unit" value={firstOption} onChange={(e) => setFirstOption(e.target.value)}>
          {Object.keys(unitConfigs.units).map((unit) => (
            <option value={unit} key={unit}>{unit.toUpperCase()}</option>
          ))}
        </select>
        <label htmlFor="second-unit" className="text-lg font-medium">Unit to convert to</label>
        <select className="border rounded-sm mb-4" name="second-unit" id="second-unit" defaultValue={Object.keys(unitConfigs.units)[1]}>
          {Object.keys(unitConfigs.units).map((unit) => 
            unit.toString() !== firstOption ? <option value={unit} key={unit}>{unit.toUpperCase()}</option> : null
          )}
        </select>
        <button type="submit" className="border p-2 rounded-md mt-4 cursor-pointer transition-colors hover:bg-black hover:text-white">Convert</button>
      </form>
    </div>
  );
}

export default GenericConverterForm;