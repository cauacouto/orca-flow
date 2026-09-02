package com.couto.OrcaFlow.service;

import com.couto.OrcaFlow.domin.Cliente;
import com.couto.OrcaFlow.domin.ItemOrcamento;
import com.couto.OrcaFlow.domin.Orcamento;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class pdfService {


    public byte[] gerarDocumento(Orcamento orcamento, Cliente cliente) throws DocumentException {

        try {
            ByteArrayOutputStream outpud = new ByteArrayOutputStream();

            Document document = new Document();

            PdfWriter.getInstance(document,outpud);

            document.open();

            //titulo

            Font titulofont = new Font(
                    Font.FontFamily.HELVETICA,
                    20,
                    Font.BOLD
            );


            document.add(new Paragraph("ORÇAMENTO",titulofont));

            document.add(new Paragraph("cliente" + cliente.getNome())
            );

            document.add(new Paragraph("validade" + orcamento.getValidade())
            );
            document.add(new Paragraph(" "));

            //tabela

            PdfPTable tabela = new PdfPTable(4);

            tabela.setWidthPercentage(100);

            tabela.addCell("Descrição");
            tabela.addCell("quantidade");
            tabela.addCell("valor unitario");
            tabela.addCell("valor total");

            for (ItemOrcamento item : orcamento.getItems()){
                tabela.addCell(item.getDescricao());
                tabela.addCell(
                        String.valueOf(item.getQuantidade())
                );
                tabela.addCell("R$" + item.getValorUnitario());
                tabela.addCell("R$" + item.getTotal());
            }
            document.add(tabela);

            // total

            Font totalfont = new Font(
            Font.FontFamily.HELVETICA,
            14,
            Font.BOLD);

            document.add(new Paragraph("TOTAL: R$ " + orcamento.getTotal(),
            totalfont)
            );

            if (orcamento.getObservacao() != null &&
                    !orcamento.getObservacao().isBlank()) {

                document.add(new Paragraph(" "));

                document.add(
                        new Paragraph(
                                "Observação: " +
                                        orcamento.getObservacao()
                        )
                );
            }
         document.close();

           return outpud.toByteArray();

        }catch (DocumentException e){
            throw  new RuntimeException("erro ao gerar pdf do orçamento",e);
        }
    }
}
