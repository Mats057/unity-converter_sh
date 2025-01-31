import { useState } from 'react'
import './App.css'
import GenericConverterForm from './components/GenericConverterForm';

function App() {
  const [selectedUnitType, setSelectedUnitType] = useState('Length');

  const UnitTypesConfigs = {
    Length: {
      name: 'Length',
      units: {
        meter: "m",
        kilometer: "km",
        centimeter: "cm",
        millimeter: "mm",
        mile: "mi",
        yard: "yd",
        foot: "ft",
        inch: "in",
      },
    },
    Weight: {
      name: 'Weight',
      units: {
        kilogram: "kg",
        gram: "g",
        milligram: "mg",
        pound: "lb",
        ounce: "oz",
      },
    },
    Temperature: {
      name: 'Temperature',
      units: {
        celsius: "°C",
        fahrenheit: "°F",
        kelvin: "K",
      },
    },
  }



  return (
    <>
      <main className='max-w-md mx-auto mt-24 p-4 border-2 rounded-lg shadow-md bg-white'>
        <div>
          <h1 className='text-4xl font-semibold'>Unit Converter</h1>
          <nav className='border-b-2 pb-2 mb-6'>
            <ul className='flex select-none space-x-4 mt-4 [&>li]:cursor-pointer  [&>li]:hover:text-blue-500 [&>li]:text-xl'>
              {Object.keys(UnitTypesConfigs).map((unitType) => (
                <li key={unitType} className={selectedUnitType === unitType ? 'text-blue-500 underline' : ''}>
                  <a onClick={() => setSelectedUnitType(unitType)}>{unitType}</a>
                </li>
              ))}
            </ul>
          </nav>
        </div>
        {selectedUnitType && <GenericConverterForm unitConfigs={UnitTypesConfigs[selectedUnitType]} />}
      </main>
    </>
  )
}

export default App
