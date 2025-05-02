"use client";

const requestOptions = {
  method: "GET",
  redirect: "follow",
};

// components/ApiService.js
export const ApiService = {
  getArray: async (arrayNumber) => {
    const response = await fetch(
      `http://localhost:8080/api/array/${arrayNumber}`,
      requestOptions
    ).catch((error) => console.error(error));

    const data = await response.json();
    return data;
  },

  getSortedArray: async (arrayNumber) => {
    const response = await fetch(
      `http://localhost:8080/api/array/sorted/${arrayNumber}`,
      requestOptions
    ).catch((error) => console.error(error));

    const data = await response.json();
    return data;
  },

  sortAlgorithm: async (algorithm, arrayNumber) => {
    const response = await fetch(
      `http://localhost:8080/api/sort?algorithm=${algorithm}&arrayNumber=${arrayNumber}`,
      requestOptions
    ).catch((error) => console.error(error));

    const data = await response.json();
    return data;
  },

  searchAlgorithm: async (arrayNumber, element) => {
    const response = await fetch(
      `http://localhost:8080/api/search?&arrayNumber=${arrayNumber}&element=${element}`,
      requestOptions
    ).catch((error) => console.error(error));

    const data = await response.json();
    return data;
  },
};
