function fn() {
  var env = 'qa'

  var config = {
    env: env
  }
  if (env == 'qa') {
    config.baseUrl = 'https://serverest.dev/';
  } else if (env == 'dev') {
    config.baseUrl = 'https://dev-serverest.dev/';
  }
  return config;
}