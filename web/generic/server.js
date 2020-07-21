const express = require('express')
const next = require('next')
///const generator = require('./generator')
const port = parseInt(process.env.PORT, 10) || 8080
const dev = process.env.NODE_ENV !== 'production'
const app = next({ dev })
const handle = app.getRequestHandler()
var bodyParser = require('body-parser');
const logger = require("./tool_server/logger")(__filename);

app.prepare()
  .then(() => {
    const server = express()
    server.use(bodyParser.urlencoded({ extended: true }));
    server.use(bodyParser.json());
    
    server.use(function (req, res, next) {
      req.url = req.originalUrl.replace('MedicalLive/_next', '_next');
      next(); 
    });

    server.get('*', (req, res) => {
      return handle(req, res)
    })

    server.listen(port, (err) => {
      if (err) throw err
      logger.info(`> Ready on http://localhost:${port}`);
    })
  })
