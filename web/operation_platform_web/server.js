require("@babel/polyfill");

const express = require('express')
const next = require('next')
const port = parseInt(process.env.PORT, 10) || 8081
const dev = process.env.NODE_ENV !== 'production';
const app = next({ dev })
const handle = app.getRequestHandler();
const bodyParser = require('body-parser');
const logger = require("./tool_server/logger")(__filename);
const {getIPAddress} = require("./tool_server/tools");

logger.info('process.env.NODE_ENV : ', process.env.NODE_ENV);




app.prepare()
  .then(() => {
    const server = express()
    server.use(bodyParser.urlencoded({ extended: true }));
    server.use(bodyParser.json());
    
    server.get('*', (req, res) => {
      return handle(req, res)
    })
    const LOCAL_IP = getIPAddress();
    server.listen(port, (err) => {
      if (err) throw err
      logger.info(`> Ready on http://${LOCAL_IP}:${port}`);
    })
  })
