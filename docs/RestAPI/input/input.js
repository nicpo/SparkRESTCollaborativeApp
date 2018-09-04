var com = { qmino : { miredot : {}}};
com.qmino.miredot.restApiSource = {"licenceType":"FREE","miredotRevision":"780bc70fd738","hideLogoOnTop":false,"allowUsageTracking":false,"licenceErrorMessage":null,"miredotVersion":"1.6.0","validLicence":true,"projectTitle":"AnalyticsApplication REST API","initialCollapseLevel":2,"jsonDocEnabled":false,"projectVersion":"1.0-SNAPSHOT","singlePage":false,"jsonDocHidden":true,"buildSystem":"maven 3","projectName":"Span Maven Webapp","dateOfGeneration":"2015-04-15 14:58:33"};
com.qmino.miredot.restApiSource.tos = {
	com_np_metastore_data_Dataset_in: { "type": "complex", "name": "com_np_metastore_data_Dataset_in", "content": [] },
	com_np_metastore_data_Dataset_out: { "type": "complex", "name": "com_np_metastore_data_Dataset_out", "content": [] },
	com_np_metastore_data_Analysis_in: { "type": "complex", "name": "com_np_metastore_data_Analysis_in", "content": [] },
	com_np_metastore_data_Analysis_out: { "type": "complex", "name": "com_np_metastore_data_Analysis_out", "content": [] },
	com_np_ws_CorrelationRequest_in: { "type": "complex", "name": "com_np_ws_CorrelationRequest_in", "content": [] },
	com_np_ws_CorrelationRequest_out: { "type": "complex", "name": "com_np_ws_CorrelationRequest_out", "content": [] },
	com_np_ws_CSVImportRequest_in: { "type": "complex", "name": "com_np_ws_CSVImportRequest_in", "content": [] },
	com_np_ws_CSVImportRequest_out: { "type": "complex", "name": "com_np_ws_CSVImportRequest_out", "content": [] },
	com_np_metastore_data_User_in: { "type": "complex", "name": "com_np_metastore_data_User_in", "content": [] },
	com_np_metastore_data_User_out: { "type": "complex", "name": "com_np_metastore_data_User_out", "content": [] },
	com_np_metastore_data_Comment_in: { "type": "complex", "name": "com_np_metastore_data_Comment_in", "content": [] },
	com_np_metastore_data_Comment_out: { "type": "complex", "name": "com_np_metastore_data_Comment_out", "content": [] },
	com_np_spark_data_CorrelationRequest_in: { "type": "complex", "name": "com_np_spark_data_CorrelationRequest_in", "content": [] },
	com_np_spark_data_CorrelationRequest_out: { "type": "complex", "name": "com_np_spark_data_CorrelationRequest_out", "content": [] },
	com_np_metastore_data_Permission_in: { "type": "complex", "name": "com_np_metastore_data_Permission_in", "content": [] },
	com_np_metastore_data_Permission_out: { "type": "complex", "name": "com_np_metastore_data_Permission_out", "content": [] },
	com_np_metastore_data_Audit_in: { "type": "complex", "name": "com_np_metastore_data_Audit_in", "content": [] },
	com_np_metastore_data_Audit_out: { "type": "complex", "name": "com_np_metastore_data_Audit_out", "content": [] }
};

com.qmino.miredot.restApiSource.enums = {

};
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_in"].content = [ 
	{
		"name": "name",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "timestamp",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "description",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "url",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "query",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "table",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "info",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_in"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_out"].content = [ 
	{
		"name": "name",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "table",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "query",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "timestamp",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "description",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "info",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "url",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_out"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_in"].content = [ 
	{
		"name": "name",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "state",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "startTime",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "type",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "output",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "request",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "endTime",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "query",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_in"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_out"].content = [ 
	{
		"name": "name",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "state",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "type",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "query",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "request",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "startTime",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "endTime",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "output",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_out"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_ws_CorrelationRequest_in"].content = [ 
	{
		"name": "session",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "query",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "col2",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "col1",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_ws_CorrelationRequest_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_ws_CorrelationRequest_in"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_ws_CorrelationRequest_out"].content = [ 
	{
		"name": "query",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "session",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "col1",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "col2",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_ws_CorrelationRequest_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_ws_CorrelationRequest_out"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_ws_CSVImportRequest_in"].content = [ 

];
com.qmino.miredot.restApiSource.tos["com_np_ws_CSVImportRequest_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_ws_CSVImportRequest_in"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_ws_CSVImportRequest_out"].content = [ 

];
com.qmino.miredot.restApiSource.tos["com_np_ws_CSVImportRequest_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_ws_CSVImportRequest_out"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_in"].content = [ 
	{
		"name": "name",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "password",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "email",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "active",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "boolean" },
		"deprecated": false
	},
	{
		"name": "salt",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "admin",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "boolean" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_in"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_out"].content = [ 
	{
		"name": "name",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "password",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "active",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "boolean" },
		"deprecated": false
	},
	{
		"name": "salt",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "email",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "admin",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "boolean" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_out"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_in"].content = [ 
	{
		"name": "target",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "created",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "text",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "updated",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "dataset",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "analysis",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_in"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_out"].content = [ 
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "target",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "text",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "updated",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "analysis",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "dataset",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "created",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_out"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_spark_data_CorrelationRequest_in"].content = [ 
	{
		"name": "name",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "query",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "col2",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "col1",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_spark_data_CorrelationRequest_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_spark_data_CorrelationRequest_in"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_spark_data_CorrelationRequest_out"].content = [ 
	{
		"name": "name",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "query",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "col1",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "col2",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_spark_data_CorrelationRequest_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_spark_data_CorrelationRequest_out"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Permission_in"].content = [ 
	{
		"name": "type",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "dataset",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "analysis",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "creator",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Permission_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Permission_in"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Permission_out"].content = [ 
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "type",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "analysis",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "dataset",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "creator",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Permission_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Permission_out"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Audit_in"].content = [ 
	{
		"name": "timestamp",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "type",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "info",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Audit_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Audit_in"].comment = null;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Audit_out"].content = [ 
	{
		"name": "id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "type",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "timestamp",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "info",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "user",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	}
];
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Audit_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Audit_out"].comment = null;
com.qmino.miredot.restApiSource.interfaces = [
	{
		"beschrijving": "",
		"url": "/core/login",
		"http": "POST",
		"title": "Login and get user session id",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["plain/text"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "string" }, "comment": "session id"},
		"statusCodes": [],
		"hash": "159935979",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_in"], "comment": "Specify email, password, admin", "jaxrs": "BODY"}],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/import/fromCSV",
		"http": "POST",
		"title": null,
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "string" }, "comment": null},
		"statusCodes": [],
		"hash": "-1275141654",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_ws_CSVImportRequest_in"], "comment": null, "jaxrs": "BODY"}],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "List all users in the system (only admin can do it)",
		"url": "/core/list/user",
		"http": "GET",
		"title": "List all users in the system (only admin can do it)",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_out"] }, "comment": "List of users"},
		"statusCodes": [],
		"hash": "1439217098",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/core/list/permissionForUser",
		"http": "GET",
		"title": "List all permissions for specified user",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Permission_out"] }, "comment": "List of permissions"},
		"statusCodes": [],
		"hash": "-1786093867",
		"inputs": {
                "PATH": [],
                "QUERY": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "User id", "jaxrs": "QUERY"}],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/dataset/{id}",
		"http": "DELETE",
		"title": "Delete dataset",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["plain/text"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Number of deleted datasets"},
		"statusCodes": [],
		"hash": "1730117046",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Dataset id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "Only admin can do it.",
		"url": "/core/list/audit",
		"http": "GET",
		"title": "Get available audit records",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Audit_out"] }, "comment": ""},
		"statusCodes": [],
		"hash": "-1283365402",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/analysis/{id}",
		"http": "GET",
		"title": "Get analysis result by id",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_out"], "comment": ""},
		"statusCodes": [],
		"hash": "1204384167",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Analysis id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/permission/{id}",
		"http": "DELETE",
		"title": "Delete permission by id",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["plain/text"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Number of deleted items"},
		"statusCodes": [],
		"hash": "318178817",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Permission id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/import/fromCSV",
		"http": "POST",
		"title": null,
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "string" }, "comment": null},
		"statusCodes": [],
		"hash": "1762761223",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_ws_CSVImportRequest_in"], "comment": null, "jaxrs": "BODY"}],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/core/list/analysisForUser",
		"http": "GET",
		"title": "List analysis available for logged user",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_out"] }, "comment": "Array of available analysis results"},
		"statusCodes": [],
		"hash": "813051459",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/dataset/",
		"http": "POST",
		"title": "Create a new dataset",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_out"], "comment": "Created dataset"},
		"statusCodes": [],
		"hash": "-2028854045",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_in"], "comment": "Dataset. Set the following fields: name, url, table, query (optional), description, info", "jaxrs": "BODY"}],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/statistics/corr",
		"http": "POST",
		"title": null,
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "number" }, "comment": null},
		"statusCodes": [],
		"hash": "2118648482",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_ws_CorrelationRequest_in"], "comment": null, "jaxrs": "BODY"}],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/core/changePassword/{id}",
		"http": "PUT",
		"title": "Change user password",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_out"], "comment": "Updated user"},
		"statusCodes": [],
		"hash": "-1555328312",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "User id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_in"], "comment": "Updated user. Specify the following fields: password", "jaxrs": "BODY"}],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/core/list/analysis",
		"http": "GET",
		"title": "List all analysis in db, require admin permissions",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_out"] }, "comment": "Array of available analysis results"},
		"statusCodes": [],
		"hash": "912874971",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/dataset/{id}",
		"http": "GET",
		"title": "Get dataset by id",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_out"], "comment": "Dataset"},
		"statusCodes": [],
		"hash": "350144639",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Dataset id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/core/list/commentForDataset",
		"http": "GET",
		"title": "Get comments for dataset",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_out"] }, "comment": "List of comments"},
		"statusCodes": [],
		"hash": "-312213251",
		"inputs": {
                "PATH": [],
                "QUERY": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Dataset id", "jaxrs": "QUERY"}],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/user/{id}",
		"http": "PUT",
		"title": "Update user settings except password",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_out"], "comment": "Updated user"},
		"statusCodes": [],
		"hash": "43527673",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "User id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_in"], "comment": "Updated user. Specify the following fields: name, e-mail, active, admin", "jaxrs": "BODY"}],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/core/list/datasetForUser",
		"http": "GET",
		"title": "List datasets available for specified user",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_out"] }, "comment": "List of datasets"},
		"statusCodes": [],
		"hash": "-360834021",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/statistics/correlation/",
		"http": "POST",
		"title": null,
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Analysis_out"], "comment": null},
		"statusCodes": [],
		"hash": "884983806",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_spark_data_CorrelationRequest_in"], "comment": null, "jaxrs": "BODY"}],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": null, "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/core/list/dataset",
		"http": "GET",
		"title": "List all datasets in db, require admin permissions",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_out"] }, "comment": "List of datasets"},
		"statusCodes": [],
		"hash": "-453848317",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/comment/{id}",
		"http": "DELETE",
		"title": "Delete comment",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["plain/text"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Number of deleted items"},
		"statusCodes": [],
		"hash": "-1036800611",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Comment id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/user/{id}",
		"http": "DELETE",
		"title": "Delete user by id",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["plain/text"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Number of deleted items"},
		"statusCodes": [],
		"hash": "86310781",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "User id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/user/{id}",
		"http": "GET",
		"title": "Get user by id",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_out"], "comment": "User"},
		"statusCodes": [],
		"hash": "-685050856",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "User id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/analysis/{id}",
		"http": "DELETE",
		"title": "Delete analysis result",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["plain/text"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Number of deleted items"},
		"statusCodes": [],
		"hash": "-1596300402",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Analysis id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/comment/",
		"http": "POST",
		"title": "Create a new comment for analysis or dataset",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_out"], "comment": "Created comment"},
		"statusCodes": [],
		"hash": "910392899",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_in"], "comment": "Comment. Specify the following fields: text, dataset OR analysis, target (optional)", "jaxrs": "BODY"}],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/permission/",
		"http": "POST",
		"title": "Create a new permission for dataset or analysis",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Permission_out"], "comment": "Created permission with all fields filled."},
		"statusCodes": [],
		"hash": "-1254126641",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Permission_in"], "comment": "Permission. The following fields should be specified: type (VIEW, COMMENT, ANALYZE, SHARE), user, dataset, analysis.", "jaxrs": "BODY"}],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/user/",
		"http": "POST",
		"title": "Create a new user account",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_out"], "comment": "Created user"},
		"statusCodes": [],
		"hash": "-1288798137",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_User_in"], "comment": "User account data. Specify the following fields: name, e-mail, password, active, admin", "jaxrs": "BODY"}],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/dataset/{id}",
		"http": "PUT",
		"title": "Update dataset",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_out"], "comment": "Updated dataset"},
		"statusCodes": [],
		"hash": "1162515441",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Dataset id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Dataset_in"], "comment": "Dataset. Set the following fields: name, url, table, query (optional), description, info", "jaxrs": "BODY"}],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/statistics/corr",
		"http": "POST",
		"title": null,
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "simple", "typeValue": "number" }, "comment": null},
		"statusCodes": [],
		"hash": "1804357157",
		"inputs": {
                "PATH": [],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_ws_CorrelationRequest_in"], "comment": null, "jaxrs": "BODY"}],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/core/list/commentForAnalysis",
		"http": "GET",
		"title": "Get comments for analysis",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_out"] }, "comment": "List of comments"},
		"statusCodes": [],
		"hash": "-1724658230",
		"inputs": {
                "PATH": [],
                "QUERY": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Analysis id", "jaxrs": "QUERY"}],
                "BODY": [],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/comment/{id}",
		"http": "PUT",
		"title": "Update comment",
		"tags": [],
		"authors": [],
		"compressed": false,
		"deprecated": false,
		"consumes": ["application/json"],
		"produces": ["application/json"],
		"roles": [],
		"rolesAllowed": null,
		"permitAll": false,
		"output": {"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_out"], "comment": "Updated comment"},
		"statusCodes": [],
		"hash": "1688722257",
		"inputs": {
                "PATH": [{"name": "id", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Comment id", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [{"typeValue": com.qmino.miredot.restApiSource.tos["com_np_metastore_data_Comment_in"], "comment": "New comment. Specify the following field: text", "jaxrs": "BODY"}],
                "HEADER": [{"name": "sid", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Session id", "jaxrs": "HEADER"}],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	}];
com.qmino.miredot.projectWarnings = [
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAXRS_MISSING_CONSUMES",
		"description": "Interface specifies a JAXRS-BODY parameter, but does not specify a Consumes value.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_SUMMARY",
		"description": "Missing summary tag",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing parameter documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing return type documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "UNREACHABLE_RESOURCE",
		"description": "This rest interface is identical to another rest interface and therefore might be unreachable.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "PARTIAL_RESOURCE_OVERLAP",
		"description": "This rest interface (partially) hides another rest interface",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing return type documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing return type documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_SUMMARY",
		"description": "Missing summary tag",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing parameter documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing return type documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "UNREACHABLE_RESOURCE",
		"description": "This rest interface is identical to another rest interface and therefore might be unreachable.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "PARTIAL_RESOURCE_OVERLAP",
		"description": "This rest interface (partially) hides another rest interface",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_SUMMARY",
		"description": "Missing summary tag",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing parameter documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing return type documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "UNREACHABLE_RESOURCE",
		"description": "This rest interface is identical to another rest interface and therefore might be unreachable.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "PARTIAL_RESOURCE_OVERLAP",
		"description": "This rest interface (partially) hides another rest interface",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_SUMMARY",
		"description": "Missing summary tag",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing parameter documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing parameter documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing return type documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_SUMMARY",
		"description": "Missing summary tag",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing parameter documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_PARAMETER_DOCUMENTATION",
		"description": "Missing return type documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "UNREACHABLE_RESOURCE",
		"description": "This rest interface is identical to another rest interface and therefore might be unreachable.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "PARTIAL_RESOURCE_OVERLAP",
		"description": "This rest interface (partially) hides another rest interface",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_AUTHORS",
		"description": "No author(s) specified for interface.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": null,
		"entity": null
	}];
com.qmino.miredot.processErrors  = [
];

