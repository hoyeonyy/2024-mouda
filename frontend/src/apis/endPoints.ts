const getEndpoint = (string: string) => {
  return `${process.env.BASE_URL}/${string}`;
};

const ENDPOINTS = {
  moim: getEndpoint('v1/moim'),
  moims: getEndpoint('v1/moim'),
};
export default ENDPOINTS;
